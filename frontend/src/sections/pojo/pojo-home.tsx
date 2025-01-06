import { useState } from 'react'
import { GoGear } from 'react-icons/go'
import { IoTelescopeOutline } from 'react-icons/io5'
import { Box, Code, Container, HStack, Icon, Text } from '@chakra-ui/react'
import { Button } from '@/components/ui/button'
import {
  MenuContent,
  MenuItem,
  MenuRoot,
  MenuTrigger,
} from '@/components/ui/menu'
import PojoForm from './components/PojoForm'

const PojoHome = () => {
  const [setup, setSetup] = useState({ lombok: false })

  const handleOnClick = () => {
    setSetup({ lombok: !setup.lombok })
  }

  return (
    <Container p="4">
      <HStack
        gap={{ base: '2', sm: '0.5' }}
        py="2"
        flexDir={{ base: 'column', sm: 'row' }}
        justifyContent="space-evenly"
      >
        <Box w="auto" fontSize={{ lg: 'lg' }}>
          <HStack justifyContent="space-between">
            <Text>Objeto de entrada: </Text>
            <Code fontSize="md">person.id - I</Code>
          </HStack>
          <HStack justifyContent="space-between">
            <Text>Array de salida: </Text>
            <Code fontSize="md">data[n].person.id - O</Code>
          </HStack>
        </Box>
        <Box>
          <MenuRoot>
            <MenuTrigger asChild>
              <Button variant="surface" colorPalette="teal">
                <GoGear />
              </Button>
            </MenuTrigger>
            <MenuContent>
              <MenuItem
                value="lombok"
                valueText="lombok"
                onClick={handleOnClick}
              >
                <Icon fontSize="lg">
                  <IoTelescopeOutline />
                </Icon>
                <Text
                  color={setup.lombok ? 'teal.300' : 'gray.100'}
                  fontSize="lg"
                >
                  Lombok
                </Text>
              </MenuItem>
            </MenuContent>
          </MenuRoot>
        </Box>
      </HStack>
      <PojoForm lombok={setup.lombok} />
    </Container>
  )
}

export default PojoHome
