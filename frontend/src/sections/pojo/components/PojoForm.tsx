import { useEffect, useState } from 'react'
import { useActionData, useSubmit } from 'react-router-dom'
import { Textarea, VStack } from '@chakra-ui/react'
import { Field, Form, Formik } from 'formik'
import { Alert } from '@/components/ui/alert'
import { Button } from '@/components/ui/button'
import { Payload } from '@/modules/pojo/domain/model/payload'
import { ActionResponse } from '@/modules/pojo/domain/model/types'
import DownloadButton from './DownloadButton'

const initialValues: Payload = {
  text: '',
}

const PojoForm = () => {
  const submite = useSubmit()
  const placeholder =
    'person.id - I\nperson.name - I\nperson.age - I\ndata.person.id - O\ndata.person.name - O\ndata.person.age - O'

  const actionData = useActionData() as ActionResponse | undefined
  const [zipUrl, setZipUrl] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    if (actionData?.success && actionData.zipUrl !== zipUrl) {
      setZipUrl(actionData.zipUrl)
    }

    if (actionData?.success === false && actionData.error) {
      setError(actionData.error)
    }

    return () => {}
  }, [actionData, zipUrl])

  return (
    <VStack
      data-state="open"
      _open={{
        animationName: 'slide-from-top, fade-in',
        animationDuration: 'slow',
      }}
      w="96"
    >
      <Formik
        initialValues={initialValues}
        onSubmit={values => {
          try {
            const formData = new FormData()
            formData.append('payload', JSON.stringify(values))
            submite(formData, { method: 'post', action: '/pojo' })
          } catch (error) {
            console.error('Error serializing form data', error)
          }
        }}
      >
        {() => (
          <Form>
            <Field
              fontFamily="Roboto Mono"
              w="40.40vw"
              minH="60.60vh"
              as={Textarea}
              size="xl"
              name="text"
              placeholder={placeholder}
              colorPalette="teal"
              fontWeight="700"
            />
            <Button
              type="submit"
              variant="surface"
              colorPalette="teal"
              size="xl"
              w="36"
            >
              Submit
            </Button>
          </Form>
        )}
      </Formik>
      {zipUrl && <DownloadButton zipUrl={zipUrl} />}
      {error && (
        <Alert title="Request Error" status="error">
          {error}
        </Alert>
      )}
    </VStack>
  )
}

export default PojoForm
