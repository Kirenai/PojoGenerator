import { FaJava } from 'react-icons/fa6'
import { Link, Outlet, useNavigate } from 'react-router-dom'
import { Box, Container, HStack, Icon, Text, VStack } from '@chakra-ui/react'
import { Button } from '@/components/ui/button'
import { ColorModeButton } from './color-mode'

const Navbar = () => {
  const navigate = useNavigate()

  const handleOnClickHome = () => {
    navigate('/')
  }

  return (
    <>
      <VStack gap={{ base: '8' }} minH="100vh">
        <Container mt="2">
          <Box position="relative" w="full" textAlign="start">
            <Button bg="transparent" onClick={handleOnClickHome}>
              <Icon color={{ _dark: 'white', _light: 'black' }} fontSize="4xl">
                <FaJava />
              </Icon>
            </Button>
            <HStack gap="4" position="absolute" top="0" right="0">
              <Link to="/pojo">
                <Text fontSize="sm" fontWeight="medium">
                  Generator
                </Text>
              </Link>
              <ColorModeButton />
            </HStack>
          </Box>
        </Container>
        <Outlet />
      </VStack>
    </>
  )
}

export default Navbar
